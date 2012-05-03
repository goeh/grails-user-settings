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

import grails.test.mixin.*
import java.text.SimpleDateFormat

/**
 * Test UserSetting domain class.
 */
@TestFor(UserSetting)
class UserSettingTests {

    void testGroovyTruth() {
        def s = new UserSetting('foo', 'boolean.true', true)
        assert s
        assert s.toString() == 'boolean.true=true'

        s = new UserSetting('foo', 'boolean.false', false)
        assert !s
        assert s.toString() == 'boolean.false=false'
    }

    void testInteger() {
        def s = new UserSetting('foo','number.int', 42)
        assert s as Integer == 42
        assert s.toString() == 'number.int=42'
    }

    void testFloat() {
        def s = new UserSetting('foo','number.float', 42.55)
        def f = s as Float
        assert f < 42.550001 && f > 41.549999
        assert s.toString() == 'number.float=42.55'
    }

    void testDouble() {
        def s = new UserSetting('foo','number.double', 42.45)
        assert s as Double == 42.45
        assert s.toString() == 'number.double=42.45'
    }

    void testList() {
        assert (new UserSetting('foo', 'list.elements', [1,2,3, "test"]) as List) == [1,2,3, "test"]
        assert (new UserSetting('foo', 'list.empty', []) as List) == []

    }

    void testMap() {
        assert (new UserSetting('foo', 'map.elements', [one:"1", two:"2", three:"3", foo:42]) as Map) == [one:"1", two:"2", three:"3", foo:42]
        assert (new UserSetting('foo', 'map.empty', [:]) as Map) == [:]
    }

    void testDate() {
        def date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2012-02-23 04:26")
        def d = new UserSetting('foo', 'date.birth', date) as Date
        assert d.format("yyyy-MM-dd HH:mm:ss.S") == '2012-02-23 04:26:00.0'
    }

    void testNull() {
        assert new UserSetting('foo','null', null).toString() == 'null=null'
        assert new UserSetting('foo','null', null) as String == null
        assert new UserSetting('foo','null', null) as Integer == null
        assert new UserSetting('foo','null', null) as Float == null
        assert new UserSetting('foo','null', null) as Double == null
        assert new UserSetting('foo','null', null) as List == null
        assert new UserSetting('foo','null', null) as Map == null
        assert new UserSetting('foo','null', null) as Date == null
    }

}
