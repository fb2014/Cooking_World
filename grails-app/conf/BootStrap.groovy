import cooking_world.InitialisationService

class BootStrap {

    InitialisationService initialisationService
    def init = { servletContext ->
        environments {
            development {
                initialisationService.initialiserDonnees()
            }
            test {
                initialisationService.initialiserDonnees()
            }
        }
    }
    def destroy = {
    }
}
