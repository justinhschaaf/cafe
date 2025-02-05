/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.justinschaaf.cafe.scriptengine;

/**
 * An example of what a script can implement.
 */
public class InternalScriptBase {

    /**
     * Whether the script works.
     */
    private boolean functional = false;

    /**
     * Default constructor.
     *
     * @param functional Whether the script works.
     */
    public InternalScriptBase(boolean functional) {
        this.functional = functional;
    }

    /**
     * Whether the script is functional.
     *
     * @return true if the script is functional
     */
    public boolean isFunctional() {
        return this.functional;
    }

}
