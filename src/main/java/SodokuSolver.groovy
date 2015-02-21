import groovy.transform.Field

@Field final def model = (1..9)

String s= '''123456789123456789123456789123456789123456789123456789123456789123456789123456789'''

boolean valid = allNum(s) &&  exact81characters(s) && validSolution(s)

private boolean allNum(String s) {
	s ==~ /\d+/
}

private boolean exact81characters(String s) {
	s.length() == 81
}

private boolean validSolution(String s) {
	def strings = s.split("(?!^)")
	def ints = strings.collect {it as int}
	
	rowsValid(ints) && columnsValid(ints) && gridsValid(ints) 
}

private boolean rowsValid(List<Integer> ints) {
	def rows = ints.collate(9)
	
	rows.find {it.sort() != model} == null
}

private boolean columnsValid(List<Integer> ints) {
	def columns = [].withEagerDefault{[]}
	ints.eachWithIndex {o, i ->
		int index = i % 9
		columns[index] << o
	}
	columns.find { it.sort() != model} == null
}

private boolean gridsValid(List<Integer> ints) {
	def grids = [].withEagerDefault{[]}
	
	ints.eachWithIndex {o, i ->
		int index = i.intdiv(27) * 3  + (i % 9).intdiv(3)
		grids[index] << o
	}
	
	println grids
	
	grids.find { it.sort() != model} == null
}

println valid