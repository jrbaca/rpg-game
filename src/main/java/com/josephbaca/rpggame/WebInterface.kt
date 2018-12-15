package com.josephbaca.rpggame

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import j2html.TagCreator.*
import j2html.tags.ContainerTag
import spark.Request
import spark.Response
import spark.Spark.*

object WebInterface {

    @JvmStatic
    fun main(args: Array<String>) {

        port(System.getenv("PORT")?.toInt() ?: 8000)
        staticFileLocation("/WebPublic/")

        // Path that the client views
        get("/") { _, _ ->
            val title = "RPG game"
            html().with(buildHeader(title), buildBody(title))
        }

        // Path for server to respond to requests
        get("/rpgserver/") { request, response ->
            val gameWrapper: GameWrapper = getGameFromSession(request)
            val input: String? = readInputFromRequest(request)
            val gameResponse = getResponseFromGame(input, gameWrapper)
            buildResponse(response, gameResponse)
        }
    }

    private fun getGameFromSession(request: Request): GameWrapper {
        val sessionGameWrapper: GameWrapper? = request.session().attribute("game")
        return if (sessionGameWrapper != null) {
            sessionGameWrapper
        } else {
            request.session().attribute("game", GameWrapper())
            request.session().attribute("game")
        }
    }

    private fun readInputFromRequest(request: Request): String? {
        return request.queryParams("input")
    }

    private fun getResponseFromGame(input: String?, gameWrapper: GameWrapper): String {
        return if (input != null) gameWrapper.getGameReponseFromInput(input) else "Bad command"
    }

    private fun buildResponse(response: Response, gameResponse: String): ObjectNode {
        // JSON builders
        val mapper = ObjectMapper()
        val objectNode = mapper.createObjectNode()

        // Build response
        response.status(200)
        response.header("cache-control", "no-cache")
        objectNode.put("response", gameResponse)
        return objectNode
    }

    private fun buildBody(title: String): ContainerTag {
        return body().with(
            header().with(
                nav().withId("banner").withClass("mui-container-fluid").with(h1(title))
            ),
            div().withId("textOutput"),
            div().with(
                button("GO").withId("submitButton").withClass("mui-btn mui-btn--fab mui-btn--primary")
            ),
            div().withId("footer").with(
                form().with(
                    div().withClass("mui-textfield").with(
                        input()
                            .withId("entryBox")
                            .withType("text")
                            .attr("autocomplete", "off")
                            .withPlaceholder("What would you like to do?")
                    )
                )
            )
        )
    }

    private fun buildHeader(title: String): ContainerTag {
        return head().with(
            // Headers per https://www.muicss.com/
            title(title),
            meta().attr("charset", "utf-8"),
            meta().attr("http-equiv", "X-UA-Compatible").withContent("IE=edge"),
            meta().withName("viewport").withContent("width=device-width, initial-scale=1"),

            // CSS files
            link().withHref("/mui-0.9.41/css/mui.css").withRel("stylesheet").withType("text/css"),
            link().withHref("/rpggame.css").withRel("stylesheet").withType("text/css"),

            // Javascript files
            script().withType("text/javascript").withSrc("/mui-0.9.41/js/mui.min.js"),
            script().withType("text/javascript").withSrc("/jquery-3.3.1.min.js"),
            script().withType("text/javascript").withSrc("/rpggame.js")
        )
    }
}