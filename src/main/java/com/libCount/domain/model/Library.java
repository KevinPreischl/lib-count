package com.libCount.domain.model;

public class Library {
	
	private String name;
	
	public Library(String path) {
		this.name = this.normalize(path);
	}
	
	public String getName() {
		return this.name;
	}
	
	private String normalize(String path) {
		String nameWithVersion = this.extractName(path);
		return this.removeVersion(nameWithVersion);
	}
	
	private String extractName(String path) {
		return path.substring(path.lastIndexOf("/") + 1).trim();
	}
	
	private String removeVersion(String nameWithVersion) {
		return nameWithVersion.split("\\?")[0];
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Library other = (Library) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}	

}
