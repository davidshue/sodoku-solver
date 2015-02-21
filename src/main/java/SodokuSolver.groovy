def model = (1..9)

boolean valid = true


String s= '''123456789123456789123456789123456789123456789123456789123456789123456789123456789'''

valid = valid && s ==~ /\d+/ &&  s.length() == 81

def strings = s.split("(?!^)")
def ints = strings.collect {it as int}

def rows = ints.collate(9)

rows.each { row ->
    valid = valid && row.sort() == model
}

def columns = [].withEagerDefault{[]}
ints.eachWithIndex {o, i ->
    int index = i % 9
    columns[index] << o
}
columns.each { column ->
    valid = valid && column.sort() == model
}


def grids = [].withEagerDefault{[]}

ints.eachWithIndex {o, i ->
    int index = i.intdiv(27) * 3  + (i % 9).intdiv(3)
    grids[index] << o
}

println grids

grids.each { grid ->
    valid = valid && grid.sort() == model
}

println valid