group = "app.revanced"

patches {
    about {
        name = "Hyro's ReVanced Patches"
        description = "Patches template for ReVanced"
        source = "git@github.com:xhyrom/revanced-patches.git"
        author = "Hyro"
        contact = "contact@xhyrom.dev"
        website = "https://github.com/xhyrom/revanced-patches"
        license = "GNU General Public License v3.0"
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs = listOf("-Xcontext-receivers")
    }
}
