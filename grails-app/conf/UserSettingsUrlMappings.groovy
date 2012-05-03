class UserSettingsUrlMappings {

    static mappings = {
        "/userSetting/$username/$param"(controller: "userSetting", parseRequest: true) {
            action = [GET: "show", POST: "update", PUT: "update", DELETE: "delete"]
        }
    }
}
