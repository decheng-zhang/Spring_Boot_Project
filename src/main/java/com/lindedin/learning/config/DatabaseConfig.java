package com.lindedin.learning.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("com.lindedin.learning.repository")
@EnableTransactionManagement
public class DatabaseConfig {

}
