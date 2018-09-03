package com.example.springhazelcast;

import org.hibernate.SessionFactory;
import org.hibernate.stat.CollectionStatistics;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.NaturalIdCacheStatistics;
import org.hibernate.stat.QueryStatistics;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "Hibernate:application=Statistics")
public class HibernateStatisticsMBean implements Statistics {

	private Statistics statistics;

	@Autowired
	public HibernateStatisticsMBean(SessionFactory sessionFactory) {
		this.statistics = sessionFactory.getStatistics();
	}

	@ManagedOperation
	public void clear() {
		statistics.clear();
	}

	@ManagedOperation
	public EntityStatistics getEntityStatistics(String entityName) {
		return statistics.getEntityStatistics(entityName);
	}

	@ManagedOperation
	public CollectionStatistics getCollectionStatistics(String role) {
		return statistics.getCollectionStatistics(role);
	}

	@ManagedOperation
	public SecondLevelCacheStatistics getSecondLevelCacheStatistics(String regionName) {
		return statistics.getSecondLevelCacheStatistics(regionName);
	}

	@ManagedOperation
	public QueryStatistics getQueryStatistics(String hql) {
		return statistics.getQueryStatistics(hql);
	}

	@ManagedAttribute
	public long getEntityDeleteCount() {
		return statistics.getEntityDeleteCount();
	}

	@ManagedAttribute
	public long getEntityInsertCount() {
		return statistics.getEntityInsertCount();
	}

	@ManagedAttribute
	public long getEntityLoadCount() {
		return statistics.getEntityLoadCount();
	}

	@ManagedAttribute
	public long getEntityFetchCount() {
		return statistics.getEntityFetchCount();
	}

	@ManagedAttribute
	public long getEntityUpdateCount() {
		return statistics.getEntityUpdateCount();
	}

	@ManagedAttribute
	public long getQueryExecutionCount() {
		return statistics.getQueryExecutionCount();
	}

	@ManagedAttribute
	public long getQueryCacheHitCount() {
		return statistics.getQueryCacheHitCount();
	}

	@ManagedAttribute
	public long getQueryExecutionMaxTime() {
		return statistics.getQueryExecutionMaxTime();
	}

	@ManagedAttribute
	public long getQueryCacheMissCount() {
		return statistics.getQueryCacheMissCount();
	}

	@ManagedAttribute
	public long getQueryCachePutCount() {
		return statistics.getQueryCachePutCount();
	}

	@ManagedAttribute
	public long getFlushCount() {
		return statistics.getFlushCount();
	}

	@ManagedAttribute
	public long getConnectCount() {
		return statistics.getConnectCount();
	}

	@ManagedAttribute
	public long getSecondLevelCacheHitCount() {
		return statistics.getSecondLevelCacheHitCount();
	}

	@ManagedAttribute
	public long getSecondLevelCacheMissCount() {
		return statistics.getSecondLevelCacheMissCount();
	}

	@ManagedAttribute
	public long getSecondLevelCachePutCount() {
		return statistics.getSecondLevelCachePutCount();
	}

	@ManagedAttribute
	public long getSessionCloseCount() {
		return statistics.getSessionCloseCount();
	}

	@ManagedAttribute
	public long getSessionOpenCount() {
		return statistics.getSessionOpenCount();
	}

	@ManagedAttribute
	public long getCollectionLoadCount() {
		return statistics.getCollectionLoadCount();
	}

	@ManagedAttribute
	public long getCollectionFetchCount() {
		return statistics.getCollectionFetchCount();
	}

	@ManagedAttribute
	public long getCollectionUpdateCount() {
		return statistics.getCollectionUpdateCount();
	}

	@ManagedAttribute
	public long getCollectionRemoveCount() {
		return statistics.getCollectionRemoveCount();
	}

	@ManagedAttribute
	public long getCollectionRecreateCount() {
		return statistics.getCollectionRecreateCount();
	}

	@ManagedAttribute
	public long getStartTime() {
		return statistics.getStartTime();
	}

	@ManagedAttribute
	public boolean isStatisticsEnabled() {
		return statistics.isStatisticsEnabled();
	}

	@ManagedOperation
	public void setStatisticsEnabled(boolean enable) {
		statistics.setStatisticsEnabled(enable);
	}

	@ManagedOperation
	public void logSummary() {
		statistics.logSummary();
	}

	@ManagedAttribute
	public String[] getCollectionRoleNames() {
		return statistics.getCollectionRoleNames();
	}

	@ManagedAttribute
	public String[] getEntityNames() {
		return statistics.getEntityNames();
	}

	@ManagedAttribute
	public String[] getQueries() {
		return statistics.getQueries();
	}

	@ManagedAttribute
	public String[] getSecondLevelCacheRegionNames() {
		return statistics.getSecondLevelCacheRegionNames();
	}

	@ManagedAttribute
	public long getSuccessfulTransactionCount() {
		return statistics.getSuccessfulTransactionCount();
	}

	@ManagedAttribute
	public long getTransactionCount() {
		return statistics.getTransactionCount();
	}

	@ManagedAttribute
	public long getCloseStatementCount() {
		return statistics.getCloseStatementCount();
	}

	@ManagedAttribute
	public long getPrepareStatementCount() {
		return statistics.getPrepareStatementCount();
	}

	@ManagedAttribute
	public long getOptimisticFailureCount() {
		return statistics.getOptimisticFailureCount();
	}

	@ManagedAttribute
	public String getQueryExecutionMaxTimeQueryString() {
		return statistics.getQueryExecutionMaxTimeQueryString();
	}

	@Override
	@ManagedAttribute
	public NaturalIdCacheStatistics getNaturalIdCacheStatistics(String regionName) {		
		return statistics.getNaturalIdCacheStatistics(regionName);
	}

	@Override
	@ManagedAttribute
	public long getNaturalIdQueryExecutionCount() {
		return statistics.getNaturalIdQueryExecutionCount();
	}

	@Override
	@ManagedAttribute
	public long getNaturalIdQueryExecutionMaxTime() {
		return statistics.getNaturalIdQueryExecutionMaxTime();
	}

	@Override
	@ManagedAttribute
	public String getNaturalIdQueryExecutionMaxTimeRegion() {
		return statistics.getNaturalIdQueryExecutionMaxTimeRegion();
	}

	@Override
	@ManagedAttribute
	public long getNaturalIdCacheHitCount() {
		return statistics.getNaturalIdCacheHitCount();
	}

	@Override
	@ManagedAttribute
	public long getNaturalIdCacheMissCount() {
		return statistics.getNaturalIdCacheMissCount();
	}

	@Override
	@ManagedAttribute
	public long getNaturalIdCachePutCount() {
		return statistics.getNaturalIdCachePutCount();
	}

	@Override
	@ManagedAttribute
	public long getUpdateTimestampsCacheHitCount() {
		return statistics.getUpdateTimestampsCacheHitCount();
	}

	@Override
	@ManagedAttribute
	public long getUpdateTimestampsCacheMissCount() {
		return statistics.getUpdateTimestampsCacheMissCount();
	}

	@Override
	@ManagedAttribute
	public long getUpdateTimestampsCachePutCount() {
		return statistics.getUpdateTimestampsCachePutCount();
	}

}
