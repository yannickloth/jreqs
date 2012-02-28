/*
 * Copyright (C) 2012 Yannick LOTH, LittleJ [www.littlej.biz]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package biz.littlej.jreqs.predicates;

import biz.littlej.jreqs.Reqs;

import java.io.File;
import java.io.Serializable;

/**
 * Some predicates pertaining to files and directories.
 * <p/>
 * The specified input file parameters must not be {@code null}.
 *
 * @author Yannick LOTH
 * @since 0.1.0
 */
public enum FilePredicates implements Predicate<File>, Serializable {
    /**
     * Evaluates to {@code true} if the specified input {@code File} is a file.
     */
    IS_FILE {
        public boolean apply(final File inputParam) {
            Reqs.parameterCondition(Predicates.notNull(), inputParam, "Input file parameter must not be null.");
            return inputParam.isFile();
        }
    },
    /**
     * Evaluates to {@code true} if the specified input {@code File} is a directory.
     */
    IS_DIRECTORY {
        public boolean apply(final File inputParam) {
            Reqs.parameterCondition(Predicates.notNull(), inputParam, "Input file parameter must not be null.");
            return inputParam.isDirectory();
        }
    },
    /**
     * Evaluates to {@code true} if the specified input {@code File} exists.
     */
    EXISTS {
        public boolean apply(final File inputParam) {
            Reqs.parameterCondition(Predicates.notNull(), inputParam, "Input file parameter must not be null.");
            return inputParam.exists();
        }
    },
    /**
     * Evaluates to {@code true} if the specified input {@code File} is executable.
     */
    CAN_EXECUTE {
        public boolean apply(final File inputParam) {
            Reqs.parameterCondition(Predicates.notNull(), inputParam, "Input file parameter must not be null.");
            return inputParam.canExecute();
        }
    },
    /**
     * Evaluates to {@code true} if the specified input {@code File} is readable.
     */
    CAN_READ {
        public boolean apply(final File inputParam) {
            Reqs.parameterCondition(Predicates.notNull(), inputParam, "Input file parameter must not be null.");
            return inputParam.canRead();
        }
    },
    /**
     * Evaluates to {@code true} if the specified input {@code File} is writeable.
     */
    CAN_WRITE {
        public boolean apply(final File inputParam) {
            Reqs.parameterCondition(Predicates.notNull(), inputParam, "Input file parameter must not be null.");
            return inputParam.canWrite();
        }
    },
    /**
     * Evaluates to {@code true} if the specified input {@code File} is hidden.
     */
    IS_HIDDEN {
        public boolean apply(final File inputParam) {
            Reqs.parameterCondition(Predicates.notNull(), inputParam, "Input file parameter must not be null.");
            return inputParam.isHidden();
        }
    }
}