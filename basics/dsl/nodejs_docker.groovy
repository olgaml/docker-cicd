job('NodeJS Docker example') {
    scm {
        git('git://github.com/yanivomc/docker-cicd.git','master') {  node -> // is hudson.plugins.git.GitSCM
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
            tag('${GIT_REVISION,length=9}')
            registryCredentials('adbde24d-2b26-4be6-ae78-072aeb5d3514')
            buildContext('./basics/')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

