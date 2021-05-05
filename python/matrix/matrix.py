class Matrix:

    matrix: list[list[int]]

    def __init__(self, raw: str):
        self.matrix = [[int(x) for x in row.split()] for row in raw.splitlines()]

    @property
    def rows(self):
        return

# class Matrix:
#     """Reads and stores an matrix of integers and provides accessors for rows and columns"""
#     mat: list[list[int]]
#     _nrow: int
#     _ncol: int
#
#     def __init__(self, matrix_string: str):
#         """Reads a string of newline delimited rows and whitespace delimited columns into a matrix"""
#         self.mat = [[int(x) for x in line.split()] for line in matrix_string.splitlines()]
#         self._nrow = len(self.mat)
#         self._ncol = len(self.mat[0])
#         if not all(len(x) == self._ncol for x in self.mat):
#             raise ValueError("All rows must have the same number of columns")
#
#     def row(self, index: int) -> list[int]:
#         """Return row at index (1-based)"""
#         if index - 1 not in range(self._nrow):
#             raise ValueError(f"Index must be in [1, {self._nrow}]")
#         return self.mat[index - 1]
#
#     def column(self, index: int) -> list[int]:
#         """Return column at index (1-based)"""
#         if index - 1 not in range(self._ncol):
#             raise ValueError(f"Index must be in [1, {self._ncol}]")
#         return [row[index - 1] for row in self.mat]
