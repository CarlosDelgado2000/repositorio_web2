package controllers

import (
    "net/http"
    "proyecto_web2/database"
    "proyecto_web2/src/models"
    "github.com/gin-gonic/gin"
)

func GetLibros(c *gin.Context) {
    var libros []models.Libro
    database.DB.Find(&libros)
    c.JSON(http.StatusOK, gin.H{"data": libros})
}

func CreateLibro(c *gin.Context) {
    var input models.Libro
    if err := c.ShouldBindJSON(&input); err != nil {
        c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
        return
    }
    database.DB.Create(&input)
    c.JSON(http.StatusOK, gin.H{"data": input})
}
