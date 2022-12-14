package mx.unam.ciencias.myp;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.core.io.ClassPathResource;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import javax.sql.DataSource;
import org.hibernate.Hibernate;

@Configuration
public class DatabaseLoader {

    private RepositorioPerfil repositorioPerfil;
    private RepositorioInstitucion repositorioInstitucion;
    private RepositorioUsuario repositorioUsuario;
    private RepositorioAreaTrabajo repositorioAreaTrabajo;

    @Autowired
    private DataSource dataSource;

    public DatabaseLoader(RepositorioPerfil repositorioPerfil,
                          RepositorioInstitucion repositorioInstitucion,
                          RepositorioUsuario repositorioUsuario,
                          RepositorioAreaTrabajo repositorioAreaTrabajo) {
        this.repositorioPerfil = repositorioPerfil;
        this.repositorioInstitucion = repositorioInstitucion;
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioAreaTrabajo = repositorioAreaTrabajo;
    }

    @Bean
    public CommandLineRunner initializeDatabase() {
        return args -> {
            Perfil indefinido = new Perfil(1, "indefinido");
            Perfil investigador = new Perfil(2, "investigador");
            Perfil estudiante = new Perfil(3, "estudiante");
            Perfil administrador = new Perfil(4, "administrador");
            repositorioPerfil.saveAll(List.of(indefinido, investigador,
                                              estudiante, administrador));

            Institucion indefinidoInstitucion = new Institucion
                (1, "indefinido", "indefinido");
            Institucion fa = new Institucion
                (2, "Facultad de Arquitectura", "M??xico");
            Institucion fad = new Institucion
                (3, "Facultad de Artes y Dise??o", "M??xico");
            Institucion fac = new Institucion
                (4, "Facultad de Ciencias", "M??xico");
            Institucion facps = new Institucion
                (5, "Facultad de Ciencias Pol??ticas y Sociales", "M??xico");
            Institucion faca = new Institucion
                (6, "Facultad de Contadur??a y Administraci??n", "M??xico");
            Institucion fade = new Institucion
                (7, "Facultad de Derecho", "M??xico");
            Institucion fae = new Institucion
                (8, "Facultad de Econom??a", "M??xico");
            Institucion fesac = new Institucion
                (9, "Facultad de Estudios Superiores (FES) Acatl??n", "M??xico");
            Institucion fesar = new Institucion
                (10, "Facultad de Estudios Superiores (FES) Arag??n", "M??xico");
            Institucion fesc = new Institucion
                (11, "Facultad de Estudios Superiores (FES) Cuautitl??n", "M??xico");
            Institucion fesiz = new Institucion
                (12, "Facultad de Estudios Superiores (FES) Iztacala", "M??xico");
            Institucion fesza = new Institucion
                (13, "Facultad de Estudios Superiores (FES) Zaragoza", "M??xico");
            Institucion faf = new Institucion
                (14, "Facultad de Filosof??a y Letras", "M??xico");
            Institucion fai = new Institucion
                (15, "Facultad de Ingenier??a", "M??xico");
            Institucion fam = new Institucion
                (16, "Facultad de Medicina", "M??xico");
            Institucion famvz = new Institucion
                (17, "Facultad de Medicina Veterinaria y Zootecnia", "M??xico");
            Institucion famu = new Institucion
                (18, "Facultad de M??sica", "M??xico");
            Institucion fao = new Institucion
                (19, "Facultad de Odontolog??a", "M??xico");
            Institucion fap = new Institucion
                (20, "Facultad de Psicolog??a", "M??xico");
            Institucion faq = new Institucion
                (21, "Facultad de Qu??mica", "M??xico");
            repositorioInstitucion.saveAll(List.of(indefinidoInstitucion, fa, fad,
                                                   fac, facps, faca, fade, fae, fesac,
                                                   fesar, fesc, fesiz, fesza, faf,
                                                   fai, fam, famvz, famu, fao, fap,
                                                   faq));

            AreaTrabajo indefinidoArea = new AreaTrabajo(1, "Indefinido");
            AreaTrabajo biologia = new AreaTrabajo(2, "Biolog??a");
            AreaTrabajo fisica = new AreaTrabajo(3, "F??sica");
            AreaTrabajo cc = new AreaTrabajo(4, "Ciencias de la Computaci??n");
            AreaTrabajo matem = new AreaTrabajo(5, "Matem??ticas");
            AreaTrabajo actuaria = new AreaTrabajo(6, "Actuar??a");
            AreaTrabajo ct = new AreaTrabajo(7, "Ciencias de la Tierra");
            AreaTrabajo fil = new AreaTrabajo(8, "Filosof??a");
            AreaTrabajo der = new AreaTrabajo(9, "Derecho");
            AreaTrabajo psi = new AreaTrabajo(10, "Psicolog??a");
            AreaTrabajo med = new AreaTrabajo(11, "Medicina");
            AreaTrabajo mus = new AreaTrabajo(12, "M??sica");
            AreaTrabajo vet = new AreaTrabajo(13, "Veterinaria");
            AreaTrabajo ing = new AreaTrabajo(14, "Ingenier??a");
            AreaTrabajo con = new AreaTrabajo(15, "Contadur??a");
            AreaTrabajo cp = new AreaTrabajo(16, "Ciencias Pol??ticas y Sociales");
            AreaTrabajo ar = new AreaTrabajo(17, "Arquitectura");
            repositorioAreaTrabajo.saveAll(List.of(indefinidoArea, biologia,
                                                   fisica, cc, matem, actuaria,
                                                   ct, fil, der, psi, med, mus,
                                                   vet, ing, con, cp, ar));
        };
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        ResourceDatabasePopulator usuarios = new ResourceDatabasePopulator
            (false, false, "UTF-8", new ClassPathResource("sql_scripts/1_usuarios.sql"));
        usuarios.execute(dataSource);

        ResourceDatabasePopulator articulos = new ResourceDatabasePopulator
            (false, false, "UTF-8", new ClassPathResource("sql_scripts/2_articulos.sql"));
        articulos.execute(dataSource);

        ResourceDatabasePopulator proyectos = new ResourceDatabasePopulator
            (false, false, "UTF-8", new ClassPathResource("sql_scripts/3_proyectos.sql"));
        proyectos.execute(dataSource);

        ResourceDatabasePopulator revistas = new ResourceDatabasePopulator
            (false, false, "UTF-8", new ClassPathResource("sql_scripts/4_revistas.sql"));
        revistas.execute(dataSource);

        ResourceDatabasePopulator articulos_usuarios = new ResourceDatabasePopulator
            (false, false, "UTF-8", new ClassPathResource("sql_scripts/articulos_usuarios.sql"));
        articulos_usuarios.execute(dataSource);

        ResourceDatabasePopulator instituciones_usuarios = new ResourceDatabasePopulator
            (false, false, "UTF-8", new ClassPathResource("sql_scripts/instituciones_usuarios.sql"));
        instituciones_usuarios.execute(dataSource);

        ResourceDatabasePopulator proyectos_usuarios = new ResourceDatabasePopulator
            (false, false, "UTF-8", new ClassPathResource("sql_scripts/proyectos_usuarios.sql"));
        proyectos_usuarios.execute(dataSource);

        ResourceDatabasePopulator revistas_articulos = new ResourceDatabasePopulator
            (false, false, "UTF-8", new ClassPathResource("sql_scripts/revistas_articulos.sql"));
        revistas_articulos.execute(dataSource);

        ResourceDatabasePopulator revistas_usuarios = new ResourceDatabasePopulator
            (false, false, "UTF-8", new ClassPathResource("sql_scripts/revistas_usuarios.sql"));
        revistas_usuarios.execute(dataSource);
    }

    private void agregaArticulosUsuarios() {
        Iterable<Usuario> usuarios = repositorioUsuario.findAll();
        Institucion institucion;
        for (Usuario u : usuarios) {
            institucion = u.getInstitucion();
            institucion.agregaUsuario(u);
        }
    }

}
