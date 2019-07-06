const Generator = require('yeoman-generator');

module.exports = class extends Generator {
  /**
   * Provides prompts for scaffolding
   * @return {Promise}
   */
  prompting() {
    const prompts = [
      {
        type: 'input',
        name: 'projectName',
        message: '(1/5) What is the name of your project?',
        default: this.appname,
      },
      {
        type: 'input',
        name: 'namespace',
        message: '(2/5) What is the namespace for your deployment?',
      },
      {
        type: 'input',
        name: 'friendlyName',
        message: '(3/5) What do you want to call your app?',
        default: this.appname.split(/-_/)[1],
      },
      {
        type: 'input',
        name: 'appGroup',
        message: '(4/5) What is the name of your app group?',
        default: this.appname.split(/-_/)[0],
      },
      {
        type: 'input',
        name: 'imageName',
        message: '(5/5) What is the name(:version) of the image?',
        default: `dh/${this.appname}:local`,
      },
    ];

    return this.prompt(prompts).then((answers) => {
      this.answers = answers;
    });
  }

  /**
   * Write out files for scaffolding
   */
  writing() {
    this._generateDeployment();
  }

  /**
   * Generates kubernetes deployment file
   */
  _generateDeployment() {
    this.fs.copyTpl(
        this.templatePath('*.*'),
        this.destinationPath(),
        this.answers
    );
  }
};
