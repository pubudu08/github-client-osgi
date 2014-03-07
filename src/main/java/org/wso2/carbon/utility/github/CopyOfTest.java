package org.wso2.carbon.utility.github;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;

public class CopyOfTest {
	public static void main(String[] args) {

		String repositoryName = "test class";
		RepositoryService service = new RepositoryService();
		service.getClient().setCredentials("pubudu08", "lasal08?");
        Repository repo = new Repository();
        repo.generateId();
        repo.setName(repositoryName);
		// Here need to add naming logic

		repositoryName  = repositoryName.replaceAll(" ", "-");

        List<Repository> listOfRepo = null;
		try {
            //service.createRepository(repo);
            System.out.println("Repo has been created ");
            listOfRepo = service.getRepositories();
		} catch (IOException e) {
			System.out.println(e);
		}
        for (Repository repository : listOfRepo) {
        	System.out.println(repository.getName());
        	if(repository.getName().equals(repositoryName)){
        	/*	System.out.println("fdsfsdfs");*/
			}
        }
	}

}
