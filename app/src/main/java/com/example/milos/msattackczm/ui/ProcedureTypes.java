/*  
Copyright (C) 2013, 2014 EP Studios, Inc.
www.epstudiossoftware.com

This file is part of EP Coding.

    EP Coding is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    EP Coding is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with EP Coding.  If not, see <http://www.gnu.org/licenses/>.

    Note also:

    CPT copyright 2012 American Medical Association. All rights
    reserved. CPT is a registered trademark of the American Medical
    Association.

    A limited number of CPT codes are used in this program under the Fair Use
    doctrine of US Copyright Law.  See README.md for more information.
 */

package com.example.milos.msattackczm.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcedureTypes {

	/**
	 * Helper class for providing sample content for user interfaces created by
	 * Android template wizards.
	 * <p>
	 */

	/**
	 * An array of sample (dummy) items.
	 */
	public static final List<ProcedureType> ITEMS = new ArrayList<ProcedureType>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static final Map<String, ProcedureType> ITEM_MAP = new HashMap<String, ProcedureType>();

	static {
		// Add 6 sample items.
		addItem(new ProcedureType("0", "Ztráta zraku na jednom oku"));
		addItem(new ProcedureType("1", "Dvojité vidění"));
		addItem(new ProcedureType("2", "Slabost a necitlivost "));
		addItem(new ProcedureType("3", "Problémy s chůzí"));
		addItem(new ProcedureType("4", "Nevolnosti/nerovnováha"));
        addItem(new ProcedureType("4", "Popis attacku"));
	}

	private static void addItem(ProcedureType item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class ProcedureType {
		public final String id;
		public final String content;

		public ProcedureType(String id, String content) {
			this.id = id;
			this.content = content;
		}

		@Override
		public String toString() {
			return content;
		}
	}
}
