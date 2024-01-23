java_binary(
    name = "demo",
    srcs = glob(["**/*.java"]),
    resources = [
        "//src/test/resources"
    ],
    deps = [
        "//src/main/java/world/rifted/libmmo/item",
        "@maven//:org_jetbrains_annotations",
        "@maven//:dev_hollowcube_minestom_ce",
        "@maven//:com_google_auto_service_auto_service",
        "@maven//:com_google_auto_service_auto_service_annotations",
        "@maven//:com_mojang_datafixerupper",
        "@maven//:net_kyori_adventure_api",
    ],
    runtime_deps = [
        "@maven//:ch_qos_logback_logback_classic",
    ],
    main_class = "world.rifted.libmmo.demo.Demo",
    plugins = [
        ":autoservice_processor",
    ],
)

java_plugin(
    name = "autoservice_processor",
    processor_class = "com.google.auto.service.processor.AutoServiceProcessor",
    deps = [
        "@maven//:com_google_auto_service_auto_service",
    ],
)