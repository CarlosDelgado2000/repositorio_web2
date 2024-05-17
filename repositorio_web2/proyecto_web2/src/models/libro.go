package models

type Libro struct {
    ID             uint   `gorm:"primaryKey"`
    Titulo         string `gorm:"size:255"`
    Autor          string `gorm:"size:255"`
    GeneroID       uint
    AnoPublicacion int
    NumEjemplares  int
    EditorialID    uint
}
