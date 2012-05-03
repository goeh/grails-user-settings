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

import org.springframework.transaction.annotation.Transactional

class UserSettingsService {

    static transactional = true

    def update(String username, String key, Object value) {
        def s = UserSetting.findByUsernameAndKey(username, key)
        if (s != null) {
            s.value = value
        } else {
            s = new UserSetting(username, key, value).save(failOnError: true)
        }
        return s.value
    }

    @Transactional(readOnly = true)
    def getValue(String username, String key) {
        UserSetting.findByUsernameAndKey(username, key)?.value
    }
}
