/*
 * Copyright (c) 2009-2016 Brennan Spies
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
package org.workhorse.activity;

import org.workhorse.exec.ctx.MutableContext;
import org.workhorse.exec.ctx.ReadValues;
import org.workhorse.process.ProcessElement;

import java.util.UUID;

/**
 * Base implementation of an {@link AtomicActivity}.
 *
 * @author Brennan Spies
 */
abstract class BaseAtomicActivity extends ProcessElement implements AtomicActivity
{
	private String name, description;
	private MutableContext context;

    /**
     * Creates the activity with a name.
	 *
	 * @param id The activity id
     * @param name The name of the activity
     */
	BaseAtomicActivity(UUID id, String name, MutableContext context) {
		super(id);
		this.name = name;
		this.context = context;
	}

	/**
	 * Gets the activity's context.
	 * @return The context
     */
	@Override public MutableContext getContext() {
		return context;
	}

	@Override
	public ReadValues getReadParentValues() {
		return null;
	}

	/** {@inheritDoc} */
	@Override public String getName() {
		return name;
	}

	/** {@inheritDoc} */
	@Override public void setName(String name) {
		this.name = name;
	}

    /** {@inheritDoc} */
	@Override public String getDescription() {
		return description;
	}

    /** {@inheritDoc} */
	@Override public void setDescription(String description) {
		this.description = description;
	}
}
