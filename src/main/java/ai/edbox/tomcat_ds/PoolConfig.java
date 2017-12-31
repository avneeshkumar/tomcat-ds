package ai.edbox.tomcat_ds;

public class PoolConfig {
	  private String poolName = "";
	  private String dataSourceClassName = "";
	  private String url = "";
	  private String username = "";
	  private String password = "";
	  private int minimumIdle = 20;
	  private int maximumPoolSize = 20;
	  private boolean autoCommit = true;
	  private int connectionTimeout = 30000;
	  private int idleTimeout = 600000;
	  private String connectionTestQuery = "select 1";
	  private boolean rewriteBatchedStatements = true;
	  private boolean cachePrepStmts = true;
	  private boolean useServerPrepStmts = true;
	  private int prepStmtCacheSize = 300;
	  private int prepStmtCacheSqlLimit = 2048;
	  private int leakDetectionThreshold = 30000;
	  
	  public int getLeakDetectionThreshold()
	  {
	    return this.leakDetectionThreshold;
	  }
	  
	  public void setLeakDetectionThreshold(int leakDetectionThreshold)
	  {
	    this.leakDetectionThreshold = leakDetectionThreshold;
	  }
	  
	  public int getMinimumIdle()
	  {
	    return this.minimumIdle;
	  }
	  
	  public void setMinimumIdle(int minimumIdle)
	  {
	    this.minimumIdle = minimumIdle;
	  }
	  
	  public String getPoolName()
	  {
	    return this.poolName;
	  }
	  
	  public void setPoolName(String poolName)
	  {
	    this.poolName = poolName;
	  }
	  
	  public String getDataSourceClassName()
	  {
	    return this.dataSourceClassName;
	  }
	  
	  public void setDataSourceClassName(String dataSourceClassName)
	  {
	    this.dataSourceClassName = dataSourceClassName;
	  }
	  
	  public String getUrl()
	  {
	    return this.url;
	  }
	  
	  public void setUrl(String url)
	  {
	    this.url = url;
	  }
	  
	  public String getUsername()
	  {
	    return this.username;
	  }
	  
	  public void setUsername(String username)
	  {
	    this.username = username;
	  }
	  
	  public String getPassword()
	  {
	    return this.password;
	  }
	  
	  public void setPassword(String password)
	  {
	    this.password = password;
	  }
	  
	  public int getMaximumPoolSize()
	  {
	    return this.maximumPoolSize;
	  }
	  
	  public void setMaximumPoolSize(int maximumPoolSize)
	  {
	    this.maximumPoolSize = maximumPoolSize;
	  }
	  
	  public boolean isAutoCommit()
	  {
	    return this.autoCommit;
	  }
	  
	  public void setAutoCommit(boolean autoCommit)
	  {
	    this.autoCommit = autoCommit;
	  }
	  
	  public boolean isRewriteBatchedStatements()
	  {
	    return this.rewriteBatchedStatements;
	  }
	  
	  public void setRewriteBatchedStatements(boolean rewriteBatchedStatements)
	  {
	    this.rewriteBatchedStatements = rewriteBatchedStatements;
	  }
	  
	  public boolean isCachePrepStmts()
	  {
	    return this.cachePrepStmts;
	  }
	  
	  public void setCachePrepStmts(boolean cachePrepStmts)
	  {
	    this.cachePrepStmts = cachePrepStmts;
	  }
	  
	  public boolean isUseServerPrepStmts()
	  {
	    return this.useServerPrepStmts;
	  }
	  
	  public void setUseServerPrepStmts(boolean useServerPrepStmts)
	  {
	    this.useServerPrepStmts = useServerPrepStmts;
	  }
	  
	  public int getConnectionTimeout()
	  {
	    return this.connectionTimeout;
	  }
	  
	  public void setConnectionTimeout(int connectionTimeout)
	  {
	    this.connectionTimeout = connectionTimeout;
	  }
	  
	  public int getPrepStmtCacheSize()
	  {
	    return this.prepStmtCacheSize;
	  }
	  
	  public void setPrepStmtCacheSize(int prepStmtCacheSize)
	  {
	    this.prepStmtCacheSize = prepStmtCacheSize;
	  }
	  
	  public int getPrepStmtCacheSqlLimit()
	  {
	    return this.prepStmtCacheSqlLimit;
	  }
	  
	  public void setPrepStmtCacheSqlLimit(int prepStmtCacheSqlLimit)
	  {
	    this.prepStmtCacheSqlLimit = prepStmtCacheSqlLimit;
	  }
	  
	  public int getIdleTimeout()
	  {
	    return this.idleTimeout;
	  }
	  
	  public void setIdleTimeout(int idleTimeout)
	  {
	    this.idleTimeout = idleTimeout;
	  }
	  
	  public String getConnectionTestQuery()
	  {
	    return this.connectionTestQuery;
	  }
	  
	  public void setConnectionTestQuery(String connectionTestQuery)
	  {
	    this.connectionTestQuery = connectionTestQuery;
	  }
}
