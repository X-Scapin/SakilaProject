package isep.web.sakila.webapi.model;

import isep.web.sakila.jpa.entities.Language;

public class LanguageWO extends WebObject {
	private static final long serialVersionUID = 674178328252388930L;

	
	protected int languageId;
	protected String name;

	public LanguageWO() {
		super();
	}

	public LanguageWO(int languageId, String name) {
		super();
		this.languageId = languageId;
		this.name = name;
	}

	public LanguageWO(final Language language) {
		super();
		this.languageId = language.getLanguageId();
		this.name = language.getName();
	}

	@Override
	public String toString() {
		return "Language [id=" + this.languageId + ", Name=" + this.name +"]";
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
