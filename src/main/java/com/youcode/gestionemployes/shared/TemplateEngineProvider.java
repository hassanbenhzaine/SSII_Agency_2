package com.youcode.gestionemployes.shared;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public final class TemplateEngineProvider {
    private static TemplateEngine te;

    public static TemplateEngine getTemplateEngine() {
        if (te == null) {
            ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
            resolver.setPrefix("/templates/");
            resolver.setSuffix(".html");
            resolver.setTemplateMode(TemplateMode.HTML);

            te = new TemplateEngine();
            te.setTemplateResolver(resolver);
        }
        return te;
    }
}
