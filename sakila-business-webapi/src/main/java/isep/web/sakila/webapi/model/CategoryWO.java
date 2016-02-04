package isep.web.sakila.webapi.model;

import isep.web.sakila.jpa.entities.Category;

public class CategoryWO extends WebObject {
	private static final long serialVersionUID = 674178328252388930L;

	
	protected byte categoryId;
	protected String name;

	public CategoryWO() {
		super();
	}

	public CategoryWO(byte categoryId, String name) {
		super();
		this.categoryId = categoryId;
		this.name = name;
	}

	public CategoryWO(final Category category) {
		super();
		this.categoryId = category.getCategoryId();
		this.name = category.getName();
	}

	@Override
	public String toString() {
		return "Category [id=" + this.categoryId + ", Name=" + this.name +"]";
	}

	public byte getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(byte categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
