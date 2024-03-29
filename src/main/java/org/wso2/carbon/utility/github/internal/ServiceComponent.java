package org.wso2.carbon.utility.github.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.utility.github.service.RepositoryAdminService;
import org.wso2.carbon.utility.versioncontrol.VersionControlArtifact;


/**
 * @scr.component name="org.wso2.carbon.utility.github"
 * immediate="true"
 */
public class ServiceComponent {
	private ServiceRegistration registration;

	private static RepositoryAdminService adminService;
	private static BundleContext bundleContext;
	private static final Log logger = LogFactory.getLog(ServiceComponent.class);

	protected void activate(ComponentContext context) {
		logger.info("Version Control Service: GitHub bundle is activated");
		adminService = new RepositoryAdminService();
		bundleContext = context.getBundleContext();
		registration = context.getBundleContext().registerService(
		  VersionControlArtifact.class.getName(), adminService, null);
	}

	protected void deactivate(ComponentContext context) {
		logger.info("Version Control Service: GitHub bundle is deactivated");
		registration.unregister();
		adminService = null;
		bundleContext = null;
	}

}
