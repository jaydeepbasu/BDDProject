node {
    def app

    stage('Cloning Repository from Git to Local') {
        /* Let's make sure we have the repository cloned to our workspace */

        checkout scm
    }
	
	stage('Checking Build at Local') {
	
	/* To download all dependencies without running the project */
		/*app = sh 'mvn -s settings.xml clean verify -DskipITs=true'*/
		
		app = sh 'mvn -s settings.xml clean test'
	}

    stage('Building the image') {
        /* This builds the actual image; synonymous to
         * docker build on the command line */
		
        app = docker.build("jaydeepqa/bddproject")	

    }

    stage('Verifying the image') {
        /* Ideally, we would run a test framework against our image.
         * For this example, we're using a Volkswagen-type approach ;-) */

        app.inside {
            sh 'mvn -s settings.xml clean test'
			
        }

    }

    stage('Pushing the image') {
        /* Finally, we'll push the image with two tags:
         * First, the incremental build number from Jenkins
         * Second, the 'latest' tag.
         * Pushing multiple tags is cheap, as all the layers are reused. */
		 
        docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
			
			int number = "${env.BUILD_NUMBER}"
			sh 'docker rmi registry.hub.docker.com/jaydeepqa/bddproject:'+number+''
			
			sh 'docker rmi registry.hub.docker.com/jaydeepqa/bddproject:latest'
			
			
			/*sh 'docker rmi $(docker images -q -f dangling=true)'				*/
			/*sh 'docker login --username=jaydeepqa --password=Jaydeeppp01011987'
			sh 'docker tag bddproject jaydeepqa/bddproject:latest'
			sh 'docker push jaydeepqa/bddproject'*/
        }
    }
}