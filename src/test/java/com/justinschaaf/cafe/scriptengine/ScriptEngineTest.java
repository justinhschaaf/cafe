/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.justinschaaf.cafe.scriptengine;

import org.junit.jupiter.api.Assertions;

import java.nio.file.Path;
import java.util.Optional;

/**
 * Tests for {@link ScriptEngine}
 */
public class ScriptEngineTest {

    public static void main(String[] args) {

        try {

            // Create a new ScriptEngine instance and load the test script
            ScriptEngine engine = new ScriptEngine();
            Optional<InternalScriptBase> scriptObj = engine.loadScript(Path.of(ScriptEngineTest.class.getResource("/ScriptTest.java").toURI()));

            // Check if the script loaded properly and tha we can call it
            if (scriptObj.isPresent()) {
                Assertions.assertTrue(scriptObj.get().isFunctional());
            } else Assertions.fail("Script is not present!");

        } catch (Exception e) {
            Assertions.fail(e);
        }

    }

}
