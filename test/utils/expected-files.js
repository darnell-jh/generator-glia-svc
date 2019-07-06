const MAIN_KOTLIN_DIR = 'src/main/kotlin';
const TEST_KOTLIN_DIR = 'src/test/kotlin';
const MAIN_RESOURCES_DIR = 'src/main/resources';
const PACKAGE_FOLDER = 'com/example/app';

const expectedFiles = {
  all: [
    `${MAIN_KOTLIN_DIR}/${PACKAGE_FOLDER}/config/SwaggerConfig.kt`,
    `${MAIN_KOTLIN_DIR}/${PACKAGE_FOLDER}/config/WebMvcConfig.kt`,
    `${MAIN_KOTLIN_DIR}/${PACKAGE_FOLDER}/Application.kt`,
    `${TEST_KOTLIN_DIR}/${PACKAGE_FOLDER}/tests/medium/BaseMediumTest.kt`,
    `${MAIN_RESOURCES_DIR}/application.yml`,
    '.gitignore',
    'build.gradle',
    'docker-compose.yml',
    'gradle.properties',
    'settings.gradle',
  ],
  cmd: [
    `${MAIN_KOTLIN_DIR}/${PACKAGE_FOLDER}/services/ExampleService.kt`,
    `${MAIN_KOTLIN_DIR}/${PACKAGE_FOLDER}/domain/aggregates/ExampleAggregate.kt`,
    `${MAIN_KOTLIN_DIR}/${PACKAGE_FOLDER}/domain/events/produced/ExampleCreatedEvent.kt`,
    `${MAIN_KOTLIN_DIR}/${PACKAGE_FOLDER}/domain/events/produced/ExampleUpdatedEvent.kt`,
    `${MAIN_KOTLIN_DIR}/${PACKAGE_FOLDER}/domain/events/produced/ExampleDeletedEvent.kt`,
    `${MAIN_KOTLIN_DIR}/${PACKAGE_FOLDER}/controllers/requests/ExampleRequest.kt`,
    `${MAIN_KOTLIN_DIR}/${PACKAGE_FOLDER}/controllers/responses/ExampleResponse.kt`,
  ],
  query: [
    `${MAIN_KOTLIN_DIR}/${PACKAGE_FOLDER}/controllers/ExampleController.kt`,
    `${MAIN_KOTLIN_DIR}/${PACKAGE_FOLDER}/domain/entities/Example.kt`,
    `${MAIN_KOTLIN_DIR}/${PACKAGE_FOLDER}/domain/events/consumed/ExampleCreatedEvent.kt`,
    `${MAIN_KOTLIN_DIR}/${PACKAGE_FOLDER}/domain/events/consumed/ExampleDeletedEvent.kt`,
    `${MAIN_KOTLIN_DIR}/${PACKAGE_FOLDER}/domain/events/consumed/ExampleUpdatedEvent.kt`,
    `${MAIN_KOTLIN_DIR}/${PACKAGE_FOLDER}/domain/listeners/external/ExampleEventListener.kt`,
    `${MAIN_KOTLIN_DIR}/${PACKAGE_FOLDER}/domain/repositories/ExampleRepository.kt`,
  ],
  kubeDeployments: [
    'kubernetes.yml',
  ],
};

module.exports = {expectedFiles, MAIN_KOTLIN_DIR, TEST_KOTLIN_DIR,
  MAIN_RESOURCES_DIR, PACKAGE_FOLDER};
