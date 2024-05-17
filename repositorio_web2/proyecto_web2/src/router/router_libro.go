package router

import (
    "proyecto_web2/src/controllers"
    "github.com/gin-gonic/gin"
)

func LibroRoutes(router *gin.Engine) {
    router.GET("/libros", controllers.GetLibros)
    router.POST("/libros", controllers.CreateLibro)
}
