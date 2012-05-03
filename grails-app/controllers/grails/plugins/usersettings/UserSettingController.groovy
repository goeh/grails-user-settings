/*
 * Copyright (c) 2012 Goran Ehrsson.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */
package grails.plugins.usersettings

import grails.converters.JSON

/**
 * JSON/REST Controller for managing user settings.
 *
 * @author Goran Ehrsson
 * @since 0.1
 */
class UserSettingController {

    def userSettingsService

    def show(String username, String param) {
        def result
        try {
            result = [success: true, value: userSettingsService.getValue(username, param)]
        } catch (Exception e) {
            result = [success: false, message: e.message]
        }
        render result as JSON
    }

    def update(String username, String param, Object value) {
        def result
        try {
            result = [success: true, value: userSettingsService.update(username, param, value)]
        } catch (Exception e) {
            result = [success: false, message: e.message]
        }
        render result as JSON
    }

    def delete(String username, String param) {
        def result
        try {
            userSettingsService.update(username, param, null)
            result = [success: true, value: null]
        } catch (Exception e) {
            result = [success: false, error: e.message]
        }
        render result as JSON
    }
}
