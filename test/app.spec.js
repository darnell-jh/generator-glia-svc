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

  describe('Kubernetes Deployment', () => {
    before((done) => {
      helpers
          .run(path.join(__dirname, '../generators/kube-deployment'))
          .withOptions({
            'from-cli': true,
            'skipInstall': true,
            'skipChecks': true,
          })
          .withPrompts({
            projectName: 'my-project',
            namespace: 'my-namespace',
            friendlyName: 'project',
            appGroup: 'my',
            imageName: 'my/image',
          })
          .on('end', done);
    });

    it('should create deployment files', () => {
      assert.file(expectedFiles.kubeDeployments);
    });
  });
});
