const MAIN_KOTLIN_DIR = 'src/main/kotlin/';
const TEST_KOTLIN_DIR = 'src/test/kotlin/';
const MAIN_RESOURCES_DIR = 'src/main/resources/';
const PACKAGE_FOLDER = 'com/example/app/';

const expectedFiles = {
  command: [
    `${MAIN_KOTLIN_DIR}${PACKAGE_FOLDER}config/SwaggerConfig.kt`,
    `${MAIN_KOTLIN_DIR}${PACKAGE_FOLDER}config/WebMvcConfig.kt`,
    `${MAIN_KOTLIN_DIR}${PACKAGE_FOLDER}Application.kt`,
    `${TEST_KOTLIN_DIR}${PACKAGE_FOLDER}tests/medium/BaseMediumTest.kt`,
    `${MAIN_RESOURCES_DIR}application.yml`,
    '.gitignore',
    'build.gradle',
    'docker-compose.yml',
    'gradle.properties',
    'settings.gradle',
  ],
};

module.exports = expectedFiles;
