function Page(pageNumber) {
	var LIMIT = 10;
	this.pageNumber = pageNumber;

	this.getOffset = function() {
		return LIMIT * (this.pageNumber <=0 ? 0 : this.pageNumber - 1);
	}

	this.getLimit = function() {
		return LIMIT;
	}
}