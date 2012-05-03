/*
 * Copyright (c) 2012 Goran Ehrsson.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * under the License.
 */

package grails.plugins.usersettings

/**
 * Test UserSettingsService.
 */
class UserSettingsServiceTests extends GroovyTestCase {

    def userSettingsService

    void testSetterAndGetter() {
        userSettingsService.update('test', 'test.string', 'Foo')
        userSettingsService.update('test', 'test.int', 42)
        userSettingsService.update('test', 'test.double', 33.3333)
        userSettingsService.update('test', 'test.list', ['hello','world'])
        userSettingsService.update('test', 'test.map', [message:'hello', to:'world'])

        assert userSettingsService.getValue('test', 'test.string') == 'Foo'
        assert userSettingsService.getValue('test', 'test.int') == 42
        assert userSettingsService.getValue('test', 'test.double') == 33.3333
        assert userSettingsService.getValue('test', 'test.list') == ['hello','world']
        assert userSettingsService.getValue('test', 'test.map') == [message:'hello', to:'world']
    }
}
