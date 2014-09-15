package org.wso2.carbon.utility.github.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.wso2.carbon.utility.versioncontrol.VersionControlArtifact;
import org.wso2.carbon.utility.versioncontrol.exception.GenericArtifactException;

import java.io.IOException;
import java.util.List;


public class RepositoryAdminService implements VersionControlArtifact {

	private static final Log LOGGER = LogFactory.getLog(RepositoryAdminService.class);

	/**
	 * To Create new GitHub project.
	 *
	 * @param username
	 * @param password
	 * @param repositoryName
	 * @throws IOException
	 * @throws GenericArtifactException
	 */
	public void createRepository(String username, String password, String repositoryName)
	  throws GenericArtifactException {
		try {
			RepositoryService service = new RepositoryService();
			service.getClient().setCredentials(username, password);
			// Here need to add naming logic
			repositoryName = repositoryName.replaceAll(" ", "-");
			List<Repository> listOfRepo;
			try {
				listOfRepo = service.getRepositories();
			} catch (IOException exception) {
				LOGGER.error(" ");
				throw new GenericArtifactException("Unable to create the repository",
				  exception, "Repository_Creation_Failed");
			}
			for (Repository repository : listOfRepo) {
				if (repository.getName().equals(repositoryName)) {
					throw new GenericArtifactException(
					  "Repository is already exists, Please provide" +
					  "a suitable Repository name. ", "Repository_Already_Exists");
				}
			}
			Repository repo = new Repository();
			repo.setName(repositoryName);
			service.createRepository(repo);
			listOfRepo = service.getRepositories();
			for (Repository repository : listOfRepo) {
				if (repository.getName().equals(repositoryName)) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug(repo.getName() + " repository has been created");
					}
				}
			}
		} catch (IOException exception) {
			throw new GenericArtifactException("Unable to create the repository",
			  exception, "Repository_Creation_Failed");
		}
	}

	/**
	 * To retrieve repository type.
	 *
	 * @return github
	 */
	public String getRepositoryType() {
		return "github";
	}

}
