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
 * @author Yannick LOTH
 * @since 0.1.0
 */
public class RequirementException extends IllegalStateException {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     */
    public RequirementException() {
        super();
    }

    /**
     * Constructor with a message associated to the exception
     *
     * @param messageParam the message.
     */
    public RequirementException(final String messageParam) {
        super(messageParam);
    }

    /**
     * Constructor with a message and a cause associated to the exception
     *
     * @param messageParam the message.
     * @param causeParam   The cause throwable.
     */
    public RequirementException(final String messageParam,
                                final Throwable causeParam) {
        super(messageParam, causeParam);
    }

    /**
     * Constructor with a cause associated to the exception
     *
     * @param causeParam The cause throwable.
     */
    public RequirementException(final Throwable causeParam) {
        super(causeParam);
    }
}
