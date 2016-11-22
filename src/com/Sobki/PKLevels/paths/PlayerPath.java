package com.Sobki.PKLevels.paths;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.projectkorra.projectkorra.Element;

public class PlayerPath {
	
	private String name;
	private String description;
	private Element element;
	private int maxLevel;
	private double expGainModifier;
	private double expLossModifier;
	private Map<String, Integer> abilityLevels;
	
	
	public PlayerPath(String name) {
		this.name = name;
		this.description = "";
		this.abilityLevels = new HashMap<>();
	}
	
	/**
	 * @return the class name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return the class description
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * @return the class element
	 */
	public Element getElement() {
		return this.element;
	}
	
	/**
	 * @return the class's max level
	 */
	public int getMaxLevel() {
		return this.maxLevel;
	}
	
	/**
	 * @return the experience gain modifier for this class
	 */
	public double getExpGainModifier() {
		return this.expGainModifier;
	}
	
	/**
	 * @return the experience loss modifier for this class
	 */
	public double getExpLossModifier() {
		return this.expLossModifier;
	}
	
	
	/**
	 * @return the available abilities
	 */
	public Set<String> getAbilities() {
		return this.abilityLevels.keySet();
	}
	
	/**
	 * @return the available abilities and their appropriate level
	 */
	public Map<String, Integer> getAbilityLevels() {
		return this.abilityLevels;
	}
	
	protected void setDescription(String description) {
		this.description = description;
	}
	
	protected void setElement(Element element) {
		this.element = element;
	}
	
	protected void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}
	
	protected void setExpGainModifier(double expGainModifier) {
		this.expGainModifier = expGainModifier;
	}
	
	protected void setExpLossModifier(double expLossModifier) {
		this.expLossModifier = expLossModifier;
	}
	
	protected void setAbilityLevels(Map<String, Integer> abilityLevels) {
		this.abilityLevels = abilityLevels;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
