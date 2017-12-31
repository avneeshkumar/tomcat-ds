package ai.edbox.tomcat_ds;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NameNotFoundException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PooledDataSourceFactory {
	private static final Logger l = Logger.getLogger(PooledDataSourceFactory.class.getName());
	private static final Map<String, DataSource> dataSources = new PooledDataSources();
	private static ObjectMapper mapper = new ObjectMapper();
	
	static {
		initPool();
	}
	private static void initPool() throws ExceptionInInitializerError {
		PoolConfigList poolConfigs = null;
		String filename = System.getProperty("addbconf");
		
		if (filename == null) {
			throw new ExceptionInInitializerError(
					"Connection pool configuration file not found. Please set 'addbconf' system property to point to the configuration file.");
		}
		try {
			poolConfigs = readConfig(new FileInputStream(filename));
		} catch (Exception e) {
			throw new ExceptionInInitializerError("Unable to read connection pool configuration file " + filename
					+ ". Please make sure the file is readable.");
		}
		l.info("Loaded configuration from file " + filename);
		if (poolConfigs != null) {
			createDataSources(poolConfigs);
		}
	}

	private static PoolConfigList readConfig(InputStream in) throws Exception {
		byte[] buf = new byte[8192];
		int count = -1;
		StringBuilder sb = new StringBuilder();
		for (;;) {
			count = in.read(buf);
			if (count <= 0) {
				break;
			}
			sb.append(new String(buf, 0, count));
		}
		try {
			in.close();
		} catch (IOException e) {
		}

		PoolConfigList pcl = null;
		try {
			pcl = (PoolConfigList) mapper.readValue(sb.toString(), PoolConfigList.class);
		} catch (Exception e) {
		}
		return pcl;
	}

	private static void createDataSources(PoolConfigList poolConfigList) {
		for (int i = 0; i < poolConfigList.size(); i++) {
			PoolConfig pc = (PoolConfig) poolConfigList.get(i);
			DataSource ds = createHikariDS(pc);
			if (ds != null) {
				dataSources.put(pc.getPoolName(), ds);
			}
		}
	}

	private static DataSource createHikariDS(PoolConfig pc) {
		PoolProperties p = new PoolProperties();

		p.setName(pc.getPoolName());
		p.setUrl(pc.getUrl());// "jdbc:mysql://localhost:3306/mysql"
		p.setDriverClassName(pc.getDataSourceClassName());
		p.setUsername(pc.getUsername());
		p.setPassword(pc.getPassword());
		p.setJmxEnabled(false);
		p.setTestWhileIdle(false);
		p.setTestOnBorrow(true);
		p.setValidationQuery(pc.getConnectionTestQuery());
		p.setTestOnReturn(false);
		p.setValidationInterval(30000);
		p.setTimeBetweenEvictionRunsMillis(30000);
		p.setMaxActive(100);
		p.setInitialSize(10);
		p.setMaxWait(10000);
		p.setRemoveAbandonedTimeout(60);
		p.setMinEvictableIdleTimeMillis(30000);
		p.setMinIdle(10);
		p.setLogAbandoned(true);
		p.setRemoveAbandoned(true);
		p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
				+ "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");

		// HikariConfig config = new HikariConfig();
		// config.setPoolName(pc.getPoolName());
		// config.setDataSourceClassName(pc.getDataSourceClassName());

		/*
		 * config.setUsername(pc.getUsername());
		 * config.setPassword(pc.getPassword());
		 * config.setMinimumIdle(pc.getMinimumIdle());
		 * config.setMaximumPoolSize(pc.getMaximumPoolSize());
		 * config.setAutoCommit(pc.isAutoCommit());
		 * config.setConnectionTimeout(pc.getConnectionTimeout());
		 * config.setIdleTimeout(pc.getIdleTimeout());
		 * config.setConnectionTestQuery(pc.getConnectionTestQuery());
		 * config.addDataSourceProperty("rewriteBatchedStatements",
		 * Boolean.valueOf(pc.isRewriteBatchedStatements()));
		 * config.addDataSourceProperty("cachePrepStmts",
		 * Boolean.valueOf(pc.isCachePrepStmts()));
		 * config.addDataSourceProperty("useServerPrepStmts",
		 * Boolean.valueOf(pc.isUseServerPrepStmts()));
		 * config.addDataSourceProperty("prepStmtCacheSize",
		 * Integer.valueOf(pc.getPrepStmtCacheSize()));
		 * config.addDataSourceProperty("prepStmtCacheSqlLimit",
		 * Integer.valueOf(pc.getPrepStmtCacheSqlLimit()));
		 * config.addDataSourceProperty("url", pc.getUrl());
		 * config.setLeakDetectionThreshold(pc.getLeakDetectionThreshold());
		 * 
		 * HikariDataSource ds = null;
		 */
		DataSource ds = null;

		try {
			// ds = new HikariDataSource(config);
			ds = new DataSource(p);
		} catch (Exception e) {
			l.log(Level.SEVERE,"Error initializing data source {}"+ pc.getPoolName(), e);
		}
		return ds;
	}

	public static DataSource getDataSource(String dsname) throws NameNotFoundException {
		if (dsname == null) {
			throw new NameNotFoundException("Data source name cannot be null.");
		}
		if (!dataSources.containsKey(dsname)) {
			throw new NameNotFoundException(
					"No data source found by name '" + dsname + "'. Please chceck configuration.");
		}
		return (DataSource) dataSources.get(dsname);
	}
}
