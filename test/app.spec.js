const path = require('path');
const assert = require('yeoman-assert');
const helpers = require('yeoman-test');
const {expectedFiles, MAIN_RESOURCES_DIR} =
  require('./utils/expected-files.js');

describe('Glia Service generator', () => {
  describe('Glia Command App', () => {
    before((done) => {
      helpers
          .run(path.join(__dirname, '../generators/app'))
          .withOptions({
            'from-cli': true,
            'skipInstall': true,
            'skipChecks': true,
          })
          .withPrompts({
            projectName: 'my-project',
            projectType: 'Command',
            packageName: 'com.example.app',
            serviceName: 'Example',
          })
          .on('end', done);
    });

    it('should create default files for commands', () => {
      assert.file(expectedFiles.all);
      assert.file(expectedFiles.cmd);
    });

    it('should generate spring.application.name in camel case', () => {
      assert.fileContent(
          `${MAIN_RESOURCES_DIR}/application.yml`,
          '    name: myProject'
      );
    });
  });

  describe('Glia Query App', () => {
    before((done) => {
      helpers
          .run(path.join(__dirname, '../generators/app'))
          .withOptions({
            'from-cli': true,
            'skipInstall': true,
            'skipChecks': true,
          })
          .withPrompts({
            projectName: 'my-project',
            projectType: 'Query',
            packageName: 'com.example.app',
            serviceName: 'Example',
          })
          .on('end', done);
    });

    it('should create default files for queries', () => {
      assert.file(expectedFiles.all);
      assert.file(expectedFiles.query);
    });

    it('should generate spring.application.name in camel case', () => {
      assert.fileContent(
          `${MAIN_RESOURCES_DIR}/application.yml`,
          '    name: myProject'
      );
    });
  });
});
