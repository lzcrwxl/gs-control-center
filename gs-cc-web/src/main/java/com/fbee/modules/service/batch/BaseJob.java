package com.fbee.modules.service.batch;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;

public abstract class BaseJob {
	protected final Log logger = LogFactory.getLog(BaseJob.class);
	public abstract void executeJob() throws IOException,DocumentException;
}
