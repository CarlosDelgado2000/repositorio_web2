package router

import (
    "github.com/gin-gonic/gin"
)

func SetupRouter() *gin.Engine {
    router := gin.Default()
    LibroRoutes(router)
    return router
}
