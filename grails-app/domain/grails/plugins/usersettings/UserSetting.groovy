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

import java.text.SimpleDateFormat
import groovy.json.JsonSlurper

/**
 * This domain class hold a single user setting.
 * Internally values are stored as the String output of groovy.json.JsonOutput.
 * Limitation: The JSON string cannot exceed 2000 characters.
 *
 */
class UserSetting<T> {

    String username
    String key
    String v

    static constraints = {
        username(maxSize: 80)
        key(maxSize: 80, unique: 'username')
        v(maxSize: 2000, blank: true)
    }

    static mapping = {
        sort "key"
        key column: 'k' // key is reserved word in MySQL
        cache usage: "nonstrict-read-write"
    }

    static transients = ['value']

    UserSetting() {}

    UserSetting(String username, String key, Object value) {
        this.username = username
        this.key = key
        this.setValue(value)
    }

    String toString() {
        "$key=${this as String}"
    }

    /**
     * Returns the persisted value for this setting.
     *
     * Values are persisted as JSON strings.
     * This method returns the result of parsing the persisted value with JsonSlurper.
     * That means (almost always) the same value as was set with setValue()
     * Note: Dates are returned as String (yyyy-MM-dd'T'HH:mm:ssZ)
     * Use <code>userSetting as Date</code> to get a Date instance
     * @return the value of this setting
     */
    Object getValue() {
        v ? new JsonSlurper().parseText(v).v : null
    }

    /**
     * Set the value of this setting.
     * Values are serialized with <code>groovy.json.JsonOutput</code> and thus persisted as JSON strings.
     * @param arg the value to set
     */
    void setValue(Object arg) {
        v = groovy.json.JsonOutput.toJson([v:arg])
    }

    /**
     * Returns the Groovy Truth for the value.
     * @return true if value is true
     */
    boolean asBoolean() {
        this.getValue().asBoolean()
    }

    /**
     * Cast the value of this setting to a specific type.
     * @param clazz the type to cast to
     * @return the value
     */
    T asType(Class<T> clazz) {
        def value = this.getValue()
        if(value == null) {
            return value
        }
        if (clazz == Date) {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(value)
        }
        return value.asType(clazz)
    }

}
