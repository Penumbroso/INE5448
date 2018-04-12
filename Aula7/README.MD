Mutantes Mortos:

Classe Position

	Metodo getNumber()
	1. (LINHA 14) replaced return of integer sized value with (x == 0 ? 1 : 0) → KILLED
	Metodo biggerThan(otherTile)
	2. (LINHA 18) replaced return of integer sized value with (x == 0 ? 1 : 0) → KILLED

Classe Grid
	Metodo Grid(numberOfLines, numberOfColumns)
	3. (LINHA 10) negated conditional → KILLED
	Metodo put(line, column, element)
	4. (LINHA 18) changed conditional boundary → KILLED
	metodo get(linha, coluna)
	5. (LINHA 25) mutated return of Object value for domain/Grid::get to ( if (x != null) null 		else throw new RuntimeException ) → KILLED

Mutantes Vivos:

Classe Tile
	Metodo getNumber()
	1. (LINHA 14) replaced return of integer sized value with (x == 0 ? 1 : 0) → SURVIVED

	Teste criado: 	testCreateTileWithNumberOne

	Metodo biggerThan(otherTile)
	1. (LINHA 18) changed conditional boundary → SURVIVED
	2. (LINHA 18) negated conditional → SURVIVED
	3. (LINHA 18) replaced return of integer sized value with (x == 0 ? 1 : 0) → SURVIVED

	Testes criados: testCompareTilesBiggerWithDifferentValues
			testCompareTilesBiggerWithEqualValues
		
	Metodo equals()	
	1. (LINHA 23) negated conditional → SURVIVED
	2. (LINHA 23) replaced return of integer sized value with (x == 0 ? 1 : 0) → SURVIVED

	Teste criado: 	testCompareTilesEqualsWithDifferentValues

Classe PuzzleGame
	Metodo moveEmptyCell()	
	1. (LINHA 101) negated conditional → SURVIVED

	Teste criado: 	testMoveEmptyCellRight

