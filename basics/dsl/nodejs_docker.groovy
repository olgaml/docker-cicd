job('NodeJS Docker example') {
    scm {
        git('https://github.com/olgaml/docker-cicd.git','master') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@devophift.work')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
   
    
    steps {
        dockerBuildAndPublish {
            repositoryName('olgaml/development')
            tag('dslExam')
            registryCredentials('adbde24d-2b26-4be6-ae78-072aeb5d3514')
            buildContext('./basics/')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

