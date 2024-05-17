package main

import (
    "proyecto_web2/database"
    "proyecto_web2/src/router"
    "proyecto_web2/src/models"
    "log"
    "os"
    "github.com/joho/godotenv"
)

func main() {
    err := godotenv.Load()
    if err != nil {
        log.Fatal("Error loading .env file")
    }

    database.Connect()
    database.DB.AutoMigrate(&models.Libro{})

    r := router.SetupRouter()
    r.Run(":" + os.Getenv("PORT"))
}
