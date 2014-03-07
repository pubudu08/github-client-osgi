package org.wso2.carbon.utility.github;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;

public class Test {
	public static void main(String[] args) {
		RepositoryService service = new RepositoryService();
		service.getClient().setCredentials("cnapagoda", "1welcome$");
		Repository repo = new Repository();
        repo.setName("test");
        try{
        	service.createRepository(repo);
        	//return true;
        }catch (Exception e){
        	//return false;
        }
	}

}
