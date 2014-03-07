package org.wso2.carbon.utility.github.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.wso2.carbon.utility.versioncontrol.IRepository;

import java.io.IOException;
import java.util.List;

public class RepositoryAdminService implements IRepository {

    private static final Log logger = LogFactory.getLog(RepositoryAdminService.class);
	
	public boolean createRepository(String username, String password, String repositoryName){
		try{

        	RepositoryService service = new RepositoryService();
    		service.getClient().setCredentials(username, password);
    		Repository repo = new Repository();
            repo.setName(repositoryName);
        	service.createRepository(repo);
            logger.info(repo.getName()+" repository has been created ! ");

            List<Repository> listOfRepo = null;
            listOfRepo = service.getRepositories();
            for (Repository repository : listOfRepo) {
                System.out.println(repository.getName());
                if(repository.getName().equals(repositoryName)){
                    return true;
                }
            }

            return true;
        }catch (IOException e){
        	return false;
        }
	}
	
	public boolean isRepositoryExist(String username, String password, String repositoryName){
		RepositoryService service = new RepositoryService();
		service.getClient().setCredentials(username, password);
		
		// Here need to add naming logic
		repositoryName  = repositoryName.replaceAll(" ", "-");
		List<Repository> listOfRepo = null;
		try {
			listOfRepo = service.getRepositories();
		} catch (IOException e) {
			return false;
		}
        for (Repository repository : listOfRepo) {
        	//System.out.println(repository.getName());
        	if(repository.getName().equals(repositoryName)){
        		return true;
			}
        }        
		return false;
	}

	public String getRepositoryType() {
		return "github";
	}

}
