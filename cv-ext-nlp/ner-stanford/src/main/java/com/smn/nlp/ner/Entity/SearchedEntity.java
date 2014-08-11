package com.smn.nlp.ner.Entity;

public class SearchedEntity {
	
	private String entity;
	private int start;
	private int end;
	private float score;
	
	public SearchedEntity(String entity, int start, int end) {
		super();
		this.entity = entity;
		this.start = start;
		this.end = end;
	}
	
	
	public void setScore(float score) {
		this.score = score;
	}


	public Float getScore() {
		return score;
	}


	public String getEntity() {
		return entity;
	}
	public int getStart() {
		return start;
	}
	public int getEnd() {
		return end;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entity == null) ? 0 : entity.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchedEntity other = (SearchedEntity) obj;
		if (entity == null) {
			if (other.entity != null)
				return false;
		} else if (!entity.equals(other.entity))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "SearchedEntity [entity=" + entity + ", score=" + score + "]";
	}
	
	
	
	

}
