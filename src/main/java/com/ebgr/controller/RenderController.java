package com.ebgr.controller;

import com.ebgr.controller.dto.RenderRequest;
import com.ebgr.controller.dto.RenderResponse;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;


@RestController
public class RenderController {

    @GetMapping("/render")
    String getRender () {
        return "<form action=\"/render\" method=\"post\"><textarea name=\"description\"></textarea> <input type=\"submit\" value=\"render\"></form>";
    }

    @PostMapping("/render")
    RenderResponse postRender (@ModelAttribute RenderRequest dto) {
        //String description = "Camera (512) (5 0 0) (0 2 -10)\nAmbiental (255 255 255) (0.2)\nEsfera (1) (0 .35 0) (255 0 0) (1 0.1 1 1 1 1 1 1)";

        System.out.println(dto.description());

        String[] command = {
                "/bin/bash",
                "-c",
                String.format("scripts/main 1 \"%s\"", dto.description())
        };

        //String.format("scripts/main > saida.txt 1 \"%s\"", description)


        StringBuilder output = new StringBuilder();
        try {
            // Cria o processo para executar o comando
            //ProcessBuilder processBuilder = new ProcessBuilder(command, argument);
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true); // Redireciona erros para a saída padrão

            // Executa o processo
            Process process = processBuilder.start();

            // Lê a saída do processo
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Aguarda o processo terminar
            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new RenderResponse(1, output.toString());
        //return "renderizado";
    }
}
