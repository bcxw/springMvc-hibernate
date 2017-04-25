package repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

@Entity
@Repository
@Table(name = "menu")
public class Menu extends RepositoryEntity implements java.io.Serializable {

	private static final long serialVersionUID = -1469887602916830462L;

	@Column(name = "parentId")
	private String parentId;

	@Column(name = "parentName")
	private String parentName;

	@Column(name = "text")
	private String text;

	@Column(name = "uri")
	private String uri;

	@Column(name = "icon")
	private String icon;

	@Column(name = "leaf")
	private String leaf;

	@Column(name = "sort")
	private Integer sort;

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return this.parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getLeaf() {
		return this.leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}