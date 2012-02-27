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
package biz.littlej.jreqs;

/**
 * Exception thrown if a post-condition is not met.
 * 
 * @author Yannick LOTH
 * @since 0.1.0
 */
public class PostConditionException extends RequirementException {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     */
    public PostConditionException() {
    }

    /**
     * Constructor with a message associated to the exception.
     * 
     * @param message
     *            The message.
     */
    public PostConditionException(final String message) {
        super(message);
    }

    /**
     * Constructor with a message and a cause associated to the exception
     * 
     * @param message
     *            the message.
     * @param cause
     *            The cause throwable.
     */
    public PostConditionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with a cause associated to the exception
     * 
     * @param cause
     *            The cause throwable.
     */
    public PostConditionException(final Throwable cause) {
        super(cause);
    }
}
