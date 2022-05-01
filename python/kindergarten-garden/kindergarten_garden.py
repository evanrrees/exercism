from typing import Optional


class Garden:
    _plants: dict[str, str] = {
        plant[0]: plant
        for plant in ["Grass", "Clover", "Radishes", "Violets"]
    }
    _students = ["Alice", "Bob", "Charlie", "David",
                 "Eve", "Fred", "Ginny", "Harriet",
                 "Ileana", "Joseph", "Kincaid", "Larry"]
    student_plants: dict[str, str]

    def __init__(
        self, 
        diagram: str,
        students: Optional[list[str]] = None
    ):
        if students is None:
            students = self._students
        top, bottom = diagram.split("\n")
        self.student_plants = {
            student: top[index * 2:index * 2 + 2] + bottom[index * 2:index * 2 + 2]
            for index, student in enumerate(sorted(students))
        }

    def plants(self, student: str):
        return [self._plants[letter] for letter in self.student_plants[student]]
