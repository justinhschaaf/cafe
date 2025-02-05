/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.justinschaaf.testscript;

import com.justinschaaf.cafe.scriptengine.InternalScriptBase;

/**
 * An example of a script to be compiled and loaded at runtime.
 */
public class ScriptTest extends InternalScriptBase {

    /**
     * Creates a new instance of this object. Must be a no-args constructor with
     * the current implementation.
     */
    public ScriptTest() {
        super(true);
    }

    @Override
    public boolean isFunctional() {
        System.out.println("I can override a method!");
        return super.isFunctional();
    }

}
